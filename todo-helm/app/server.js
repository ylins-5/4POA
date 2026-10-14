const express = require('express');
const sql = require('mssql');

const app = express();
app.use(express.json());
app.use(express.static('public'));

const DB = process.env.DB_NAME || 'TodoDb';
const base = {
  server: process.env.DB_HOST,
  port: Number(process.env.DB_PORT || 1433),
  user: process.env.DB_USER || 'sa',
  password: process.env.DB_PASSWORD,
  options: { encrypt: true, trustServerCertificate: true },
};
let pool;

async function init() {
  for (;;) {
    try {
      const master = await new sql.ConnectionPool({ ...base, database: 'master' }).connect();
      await master.request().query(`IF DB_ID('${DB}') IS NULL CREATE DATABASE [${DB}]`);
      await master.close();
      pool = await new sql.ConnectionPool({ ...base, database: DB }).connect();
      await pool.request().query(`
        IF OBJECT_ID('dbo.Tasks') IS NULL
        CREATE TABLE dbo.Tasks (
          id INT IDENTITY PRIMARY KEY,
          title NVARCHAR(200) NOT NULL,
          done BIT NOT NULL DEFAULT 0
        )`);
      console.log('Banco pronto.');
      return;
    } catch (e) {
      console.log('Aguardando banco...', e.message);
      await new Promise((r) => setTimeout(r, 5000));
    }
  }
}

app.get('/healthz', (_, res) => res.send('ok'));

app.get('/api/tasks', async (_, res) => {
  const r = await pool.request().query('SELECT id, title, done FROM dbo.Tasks ORDER BY id');
  res.json(r.recordset);
});

app.post('/api/tasks', async (req, res) => {
  const r = await pool.request()
    .input('title', sql.NVarChar(200), req.body.title)
    .query('INSERT INTO dbo.Tasks (title) OUTPUT INSERTED.* VALUES (@title)');
  res.status(201).json(r.recordset[0]);
});

app.put('/api/tasks/:id', async (req, res) => {
  await pool.request()
    .input('id', sql.Int, req.params.id)
    .input('done', sql.Bit, req.body.done)
    .query('UPDATE dbo.Tasks SET done = @done WHERE id = @id');
  res.sendStatus(204);
});

app.delete('/api/tasks/:id', async (req, res) => {
  await pool.request().input('id', sql.Int, req.params.id)
    .query('DELETE FROM dbo.Tasks WHERE id = @id');
  res.sendStatus(204);
});

init();
app.listen(3000, () => console.log('Servidor na porta 3000'));

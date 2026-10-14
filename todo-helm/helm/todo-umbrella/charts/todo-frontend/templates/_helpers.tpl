{{- define "todo-frontend.fullname" -}}
{{- printf "%s-frontend" .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "todo-frontend.labels" -}}
app.kubernetes.io/name: todo-frontend
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version }}
{{- end -}}

{{- define "todo-frontend.selectorLabels" -}}
app.kubernetes.io/name: todo-frontend
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{- define "todo-frontend.dbHost" -}}
{{- default (printf "%s-sqlserver" .Release.Name) .Values.database.host -}}
{{- end -}}

{{- define "todo-frontend.dbSecret" -}}
{{- default (printf "%s-sqlserver" .Release.Name) .Values.database.secretName -}}
{{- end -}}

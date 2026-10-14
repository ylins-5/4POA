{{- define "sqlserver.fullname" -}}
{{- printf "%s-sqlserver" .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "sqlserver.labels" -}}
app.kubernetes.io/name: sqlserver
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version }}
{{- end -}}

{{- define "sqlserver.selectorLabels" -}}
app.kubernetes.io/name: sqlserver
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{- define "sqlserver.password" -}}
{{- $g := .Values.global | default dict -}}
{{- required "Defina global.saPassword (ou saPassword)" (default .Values.saPassword (dig "saPassword" "" $g)) -}}
{{- end -}}

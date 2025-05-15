{{- define "opentelemetrycollector-helm-charts.name" -}}
{{ .Chart.Name }}
{{- end }}

{{- define "otel.labels" -}}
app.kubernetes.io/name: {{ include "opentelemetrycollector-helm-charts.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
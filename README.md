# Distributed Tracing Demo with OpenTelemetry on Kubernetes

## 📌 Purpose

This project demonstrates how to instrument microservices with OpenTelemetry to collect **Distributed Tracing** data and export it to **Jaeger**, using an **OpenTelemetry Collector** deployed in **Kubernetes** via Helm Charts located in the `charts/` directory.

---

## 🧱 Architecture

```
┌─────────────────────────────┐
│      first-microservice     │
│ (Instrumented with OTEL SDK)│
└──────────────┬──────────────┘
               │
┌──────────────▼──────────────┐
│     second-microservice     │
│ (Instrumented with OTEL SDK)│
└──────────────┬──────────────┘
               │
               ▼
   ┌─────────────────────────┐
   │ OpenTelemetry Collector │
   │     (via Helm Chart)    │
   └────────────┬────────────┘
                │
                ▼
         ┌────────────┐
         │   Jaeger   │
         │ (via Helm) │
         └────────────┘
```

---

## 📁 Project Structure

- `first-microservice/`: Java service with OpenTelemetry SDK instrumentation.
- `second-microservice/`: Second service also instrumented.
- `charts/`: Helm Charts to deploy the OpenTelemetry Collector and Jaeger.

---

## 🚀 How to Run

### 1. Prerequisites

- Kubernetes (Minikube, Kind, EKS, etc)
- Helm v3+
- kubectl
- Java 17+
- Docker

### 2. Deploy Collector + Jaeger

```bash
cd charts
kubectl create namespace observability
helm install observability-stack . -n observability
```

---

## 📦 OpenTelemetry Instrumentation

Both services are instrumented using OpenTelemetry SDK:

- Context propagation via HTTP headers.
- Exporting spans using OTLP/gRPC.

---

## 🔍 Viewing Traces

1. Port-forward Jaeger:

```bash
kubectl port-forward svc/jaeger-query -n observability 16686:16686
```

2. Open [http://localhost:16686](http://localhost:16686)

---

## 🧪 Manual Test

```bash
kubectl port-forward svc/<first-microservice-service> -n observability 8080:8080
curl http://localhost:8080/greetings/hello-second-microservice
```

---

## 🔁 Extendability

- Collector can export data to other backends (Zipkin, Prometheus, NewRelic, etc).
- Sampling and span processing can be configured.

---

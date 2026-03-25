<div align="center">
🚀 Lovable Clone
AI-Powered Full-Stack Project Generation Platform
A production-ready Spring Boot (Java 21) backend that replicates platforms like Lovable and v0 by Vercel — enabling users to generate full-stack applications using AI, manage projects collaboratively, and deploy live previews dynamically.
[Show Image](https://openjdk.org/projects/jdk/21/)
[Show Image](https://spring.io/projects/spring-boot)
[Show Image](https://www.postgresql.org/)
[Show Image](https://redis.io/)
[Show Image](https://kubernetes.io/)
</div>

✨ Features
Feature Description 🤖 AI Code Generation: Multi-model support via OpenRouter (Gemini, Grok, GPT-4)💬 Persistent Chat Editing: Iterative, context-aware project editing via chat📁 Virtual File SystemAI-driven create/update/delete file operations👥 Team CollaborationProject-level RBAC for multi-user workflows🚀 Live DeploymentsDynamic preview URLs via Kubernetes💳 Subscription BillingStripe-powered plans with quota enforcement📊 Usage TrackingPer-user AI call and project limits☁️ Object StorageMinIO (S3-compatible) blob storage

🏗️ Tech Stack
Backend

Java 21 · Spring Boot (WebMVC) · Spring Security + JWT
Spring Data JPA (Hibernate) · MapStruct · Lombok

Infrastructure

PostgreSQL — Primary relational database
Redis — Caching & in-memory state
MinIO — S3-compatible object storage
Kubernetes — Dynamic container deployments
Docker Compose — Local environment orchestration

AI Layer

Spring AI + OpenRouter API
Supported models: Google Gemini, Grok, OpenAI models

Payments

Stripe API — Checkout sessions, subscription lifecycle, webhook handling


📦 Architecture Overview
User → REST API → Auth → Project Service → AI Orchestration
                                      ↓
                            File Context Injection
                                      ↓
                               Tool Call Parser
                                      ↓
                            File System Mutations
                                      ↓
                           Kubernetes Deployment
The system orchestrates:

LLM-driven code generation
Context-aware incremental file updates
Structured tool-calling execution
Live project deployments
Subscription-based usage enforcement


📁 Project Structure
com.codingshuttle.projects.lovable_clone
│
├── auth/            → Authentication & JWT
├── user/            → User management
├── project/         → Project CRUD & virtual file system
├── chat/            → Chat sessions & streaming events
├── ai/              → AI orchestration & tool call parsing
├── deployment/      → Kubernetes preview deployments
├── subscription/    → Plans, billing & usage enforcement
└── common/          → Shared configs & utilities

🔐 Authentication

Stateless JWT-based authentication
Role-based authorization
Project-level RBAC for team collaboration


🤖 AI Code Generation Flow
1. User sends prompt via Chat API
       ↓
2. System injects file tree + relevant file contents
       ↓
3. LLM responds with structured tool calls
       ↓
4. Backend parses tool calls (create / update / delete)
       ↓
5. File system mutations are applied & persisted
       ↓
6. Optional deployment triggered

🚀 Deployment System
The DeploymentService:

Builds container workloads
Pushes to Kubernetes cluster
Exposes live preview URLs
Manages container lifecycle (restart / terminate)


⚠️ Requires a connected Kubernetes cluster (local or remote).


💳 Subscription System
Plans

Free · Pro · Premium

Features

AI usage quotas per plan
Project creation limits
Export limits
Stripe checkout session creation
Webhook event handling & subscription status sync


🛠️ Local Development Setup
Prerequisites

Java 21
Docker & Docker Compose
Kubernetes cluster (local or remote)
Maven

1. Clone the Repository
bashgit clone https://github.com/your-username/lovable-clone.git
cd lovable-clone
2. Start Infrastructure Services
bashdocker-compose -f services.docker-compose.yml up -d
This starts PostgreSQL, Redis, and MinIO.
3. Configure Environment
Update application.yaml:
yamlspring:
  datasource:
    url: jdbc:postgresql://localhost:5432/lovable
    username: postgres
    password: postgres

  ai:
    openai:
      api-key: YOUR_OPENROUTER_KEY
      base-url: https://openrouter.ai/api/v1

stripe:
  api-key: YOUR_STRIPE_SECRET
  webhook-secret: YOUR_WEBHOOK_SECRET

minio:
  url: http://localhost:9000
  access-key: minio
  secret-key: minio123
4. Run the Application
bash./mvnw spring-boot:run
The app runs at http://localhost:8080.

📡 API Reference
MethodEndpointDescriptionPOST/api/v1/auth/signupRegister a new userPOST/api/v1/auth/loginAuthenticate & get JWTPOST/api/v1/projectsCreate a new projectGET/api/v1/projectsList all projectsGET/api/v1/projects/{id}Get project by IDPOST/api/v1/chat/{projectId}Send prompt to AIPOST/api/v1/deploy/{projectId}Deploy project previewPOST/api/v1/subscriptions/checkoutCreate Stripe checkout session

🧠 Key Design Concepts
Context Injection
FileTreeContextAdvisor injects the current project file tree into every AI prompt, enabling incremental updates rather than full regeneration.
Tool Calling
AI responses are parsed into structured commands by LlmResponseParser:

create_file
update_file
delete_file

Usage Enforcement
UsageService enforces plan limits by blocking:

AI calls beyond quota
Project creation beyond plan limits


🧪 Testing
bash./mvnw test

📈 Production Checklist

 Enable HTTPS / TLS termination
 Secure Stripe webhook endpoint
 Use managed PostgreSQL (e.g., AWS RDS)
 Use managed Redis (e.g., ElastiCache)
 Configure Kubernetes autoscaling (HPA)
 Persist MinIO data with durable storage
 Set up centralized logging (ELK Stack / Grafana Loki)


🗺️ Roadmap

 GitHub integration
 Code diff viewer
 Real-time collaboration (WebSockets)
 Background job queue system
 Multi-region deployments
 Project templates marketplace


🤝 Contributing

Fork the repository
Create a feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request


📄 License
Distributed under the MIT License. See LICENSE for details.

⭐ Acknowledgements
Inspired by:

Lovable
v0 by Vercel
Replit AI
Cursor


<div align="center">
  <sub>Built with ☕ Java, 🍃 Spring Boot, and a little AI magic.</sub>
</div>

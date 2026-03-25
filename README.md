🚀 Lovable Clone – AI Powered Project Generation Platform

A production-ready Spring Boot (Java 21) backend that replicates platforms like Lovable and v0 by Vercel, enabling users to generate full-stack applications using AI, manage projects collaboratively, and deploy live previews dynamically.

✨ Features
🤖 AI-powered code generation (multi-model support via OpenRouter)
💬 Persistent chat-based project editing
📁 Virtual project file system with context-aware AI updates
👥 Team collaboration with RBAC
🚀 Live preview deployments using Kubernetes
💳 Subscription billing with Stripe
📊 Usage tracking & quota enforcement
☁️ Blob storage with MinIO (S3-compatible)
🏗️ Tech Stack
Backend
Java 21
Spring Boot (WebMVC)
Spring Security + JWT
Spring Data JPA (Hibernate)
MapStruct + Lombok
Infrastructure
PostgreSQL – Primary database
Redis – Caching & memory
MinIO – Object storage (S3-compatible)
Kubernetes – Dynamic container deployments
Docker Compose – Local environment setup
AI Layer
Spring AI
OpenRouter API
Supports models like:
Google Gemini
Grok
OpenAI models
Payments
Stripe API
Checkout sessions
Subscription lifecycle
Webhook handling
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
Context-aware file updates
Structured tool-calling execution
Live project deployments
Subscription-based usage enforcement
📁 Project Structure
com.codingshuttle.projects.lovable_clone
│
├── auth/                  → Authentication & JWT
├── user/                  → User management
├── project/               → Project CRUD & file system
├── chat/                  → Chat sessions & events
├── ai/                    → AI orchestration & tool parsing
├── deployment/            → Kubernetes preview deployments
├── subscription/          → Plans, billing & usage
├── common/                → Shared configs & utilities
🔐 Authentication
JWT-based stateless authentication
Role-based authorization
Project-level RBAC for collaboration
🤖 AI Code Generation Flow
User sends a prompt via Chat API
System injects:
Current file tree
Relevant file contents
LLM responds with:
Structured tool calls (create/update/delete files)
Backend parses response
File system updates are applied
Changes are persisted
Optional deployment triggered
🚀 Deployment System

The DeploymentService:

Builds container workloads
Pushes to Kubernetes
Exposes preview URLs
Manages lifecycle (restart/terminate)

Requires a connected Kubernetes cluster.

💳 Subscription System
Plans
Free
Pro
Premium
Features
AI usage quotas
Project limits
Export limits
Stripe checkout session creation
Webhook event handling
Subscription status syncing
🛠️ Local Development Setup
1️⃣ Prerequisites
Java 21
Docker
Kubernetes cluster (local or remote)
Maven
2️⃣ Clone Repository
git clone https://github.com/your-username/lovable-clone.git
cd lovable-clone
3️⃣ Start Infrastructure Services
docker-compose -f services.docker-compose.yml up -d

This will start:

PostgreSQL
Redis
MinIO
4️⃣ Configure Environment

Update application.yaml:

spring:
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
5️⃣ Run Application
./mvnw spring-boot:run

App runs on:

http://localhost:8080
📡 API Overview
Auth
POST /api/v1/auth/signup
POST /api/v1/auth/login
Projects
POST /api/v1/projects
GET /api/v1/projects
GET /api/v1/projects/{id}
Chat & AI
POST /api/v1/chat/{projectId}
Deployment
POST /api/v1/deploy/{projectId}
Subscription
POST /api/v1/subscriptions/checkout
Stripe webhook endpoint
🧠 Key Design Concepts
Context Injection

The FileTreeContextAdvisor injects project structure into prompts to allow incremental changes rather than regeneration.

Tool Calling

AI responses are structured into actionable commands:

Create file
Update file
Delete file

Parsed via LlmResponseParser.

Usage Enforcement

UsageService blocks:

AI calls beyond quota
Project creation beyond plan limits
🧪 Testing
./mvnw test
📈 Production Considerations
Enable HTTPS
Secure Stripe webhooks
Use managed PostgreSQL
Use managed Redis
Configure Kubernetes autoscaling
Store MinIO data persistently
Add centralized logging (ELK / Grafana)
🗺️ Roadmap
 GitHub integration
 Code diff viewer
 Real-time collaboration (WebSockets)
 Background queue system
 Multi-region deployments
 Project templates marketplace
🤝 Contributing
Fork repository
Create feature branch
Commit changes
Open Pull Request
📄 License

MIT License

⭐ Acknowledgements

Inspired by platforms like:

Lovable
v0 by Vercel
Replit AI
Cursor

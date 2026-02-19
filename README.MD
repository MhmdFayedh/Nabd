# Nabd

Nabd is a content management and discovery platform built with Spring Boot, Elasticsearch, and cloud-native technologies for media content processing and search.

## 🚀 Quick Start

```bash
git clone https://github.com/MhmdFayedh/Nabd.git
cd Nabd
cp .env.example .env
docker-compose up -d
```

## 📁 Project Structure
```text
Nabd/
├── pom.xml
├── Dockerfile
├── docker-compose.yml
├── filebeat.yml
├── .gitignore
├── README.md
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── sa/
    │   │       └── mhmd/
    │   │           └── nabd/
    │   │               ├── NabdApplication.java
    │   │               ├── config/
    │   │               ├── shared/
    │   │               └── modules/
    │   │
    │   └── resources/
    │       └── application.yml
    │
    └── test/
        └── java/
            └── sa/mhmd/nabd/
```
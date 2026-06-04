# Local AI Agent URLs

## RAG Endpoints

### Ingest Knowledge File

http://localhost:8080/ingest

---

### Ingest Codebase

http://localhost:8080/ingest-codebase?path=C:%5CUsers%5CDell%5CDownloads%5CProjects%5Ctrash

---

### Search Codebase

http://localhost:8080/search?q=UserService

http://localhost:8080/search?q=getDepartment

http://localhost:8080/search?q=logging

http://localhost:8080/search?q=constructor%20injection

---

### Ask RAG

http://localhost:8080/ask-rag?q=What%20does%20UserService%20return

http://localhost:8080/ask-rag?q=How%20does%20UserService%20work

http://localhost:8080/ask-rag?q=What%20department%20is%20returned%20by%20UserService

http://localhost:8080/ask-rag?q=How%20is%20logging%20implemented

http://localhost:8080/ask-rag?q=Do%20we%20use%20constructor%20injection

---

## Generic LLM

http://localhost:8080/ask?q=What%20is%20Kafka

http://localhost:8080/ask?q=Explain%20CAP%20Theorem

http://localhost:8080/ask?q=Explain%20Kafka%20Consumer%20Groups

http://localhost:8080/ask?q=What%20is%20wave%20particle%20duality

---

## GitHub Agent

### Create Branch

http://localhost:8080/ask?q=Create%20branch%20test-agent-2%20in%20repository%20pratikvats70-lab/Spring

---

### Create File

http://localhost:8080/ask?q=Create%20file%20demo2.txt%20in%20branch%20test-agent-2%20of%20repository%20pratikvats70-lab/Spring%20with%20content%20Hello%20from%20AI

---

### Read README

http://localhost:8080/ask?q=Read%20README%20from%20repository%20pratikvats70-lab/Spring

---

### List Repository Files

http://localhost:8080/ask?q=List%20files%20in%20repository%20pratikvats70-lab/Spring

---

### Read File From Repository

http://localhost:8080/ask?q=Read%20file%20README.md%20from%20repository%20pratikvats70-lab/Spring

---

### Create Pull Request

http://localhost:8080/ask?q=Create%20a%20pull%20request%20from%20test-agent-2%20to%20main%20in%20repository%20pratikvats70-lab/Spring%20with%20title%20AI%20Agent%20PR%202%20and%20description%20Created%20by%20AI

---

## Local File Operations

### Read Local File

http://localhost:8080/ask?q=Read%20file%20C:%5CUsers%5CDell%5CDownloads%5CProjects%5Ctrash%5Csample%5CUserService.java

---

### List Local Files

http://localhost:8080/ask?q=List%20all%20files%20under%20C:%5CUsers%5CDell%5CDownloads%5CProjects%5Ctrash

---

### Create Local File

http://localhost:8080/ask?q=Create%20file%20C:%5Ctemp%5Ctest.txt%20with%20content%20Hello%20World

---

## Demo Flow

1. Ingest codebase

http://localhost:8080/ingest-codebase?path=C:%5CUsers%5CDell%5CDownloads%5CProjects%5Ctrash

2. Ask about code

http://localhost:8080/ask-rag?q=What%20does%20UserService%20return

3. Create branch

http://localhost:8080/ask?q=Create%20branch%20test-agent-1%20in%20repository%20pratikvats70-lab/Spring

4. Create file

http://localhost:8080/ask?q=Create%20file%20demo3.txt%20in%20branch%20test-agent-1%20of%20repository%20pratikvats70-lab/Spring%20with%20content%20Created%20by%20AI%20Agent

5. Update File

http://localhost:8080/ask?q=Update%20demo.txt%20in%20repository%20pratikvats70-lab/Spring%20branch%20test-agent-1%20with%20content%20This%20file%20was%20updated%20by%20AI
 
6. Raise PR

http://localhost:8080/ask?q=Create%20a%20pull%20request%20from%20test-agent-1%20to%20main%20in%20repository%20pratikvats70-lab/Spring%20with%20title%20AI%20Agent%20Demo%20PR%20and%20description%20Created%20by%20Local%20AI

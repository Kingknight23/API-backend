from fastapi import FastAPI
from app.api.v1.endpoints import payments, refunds
from app.config import settings

app = FastAPI(
    title="Payment Service",
    description="A microservice for handling payment-related operations")

# Include routes
app.include_router(payments.router, prefix="/api/v1/payments", tags=["Payments"])
app.include_router(refunds.router, prefix="/api/v1/refunds", tags=["Refunds"])

# Startup and shutdown events
@app.on_event("startup")
async def startup_event():
    # Code to run when the application starts (e.g., connecting to the DB)
    print("Starting up the service...")

@app.on_event("shutdown")
async def shutdown_event():
    # Code to run when the application shuts down (e.g., closing DB connections)
    print("Shutting down the service...")

# Root endpoint
@app.get("/")
async def root():
    return {"message": "Welcome to the Payment Service API"}

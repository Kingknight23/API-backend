from enum import Enum
from fastapi import FastAPI, Query
# from app.api.v1.endpoints import payments, refunds
# from app.config import settings

class endpoints(str, Enum):
    payments= "payment"
    refunds = "refunds"

app = FastAPI(
    title="Payment Service",
    description="A microservice for handling payment-related operations")

# Include routes
# app.include_router(payments.router, prefix="/api/v1/payments", tags=["Payments"])
# app.include_router(refunds.router, prefix="/api/v1/refunds", tags=["Refunds"])

# Startup and shutdown events

@app.on_event("startup")
async def startup_event():
    # Code to run when the application starts (e.g., connecting to the DB)
    print("Starting up the service...")

@app.on_event("shutdown")
async def shutdown_event():
    # Code to run when the application shuts down (e.g., closing DB connections)
    print("Shutting down the service...")

@app.get("/items/{item_id}")
async def read_item(item_id: int):
    return {"item_id": item_id}

@app.get("/api/vi/{end_point}")
async def get_service(end_point:endpoints ):
    # if end_point is endpoints.payments:
    #     return {"end_point": end_point, "message": f"in the {end_point} service"}
    return {"end_point": end_point, "message": f"in the {end_point.value} service"}

# Root endpoint
@app.get("/")
async def root():
    return {"message": "Welcome to the Payment Service API"}

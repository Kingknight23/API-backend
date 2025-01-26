from pydantic import BaseSettings

class Settings(BaseSettings):
    APP_NAME: str = "Payment Service"
    ENVIRONMENT: str = "development"  # Can be 'development', 'staging', or 'production'
    DATABASE_URL: str  # Example: "postgresql://user:password@localhost/dbname"
    SECRET_KEY: str  # For encrypting tokens or sensitive data
    PAYMENT_GATEWAY_API_KEY: str  # Replace with your payment gateway API key
    LOG_LEVEL: str = "info"

    class Config:
        env_file = ".env"  # Specify the .env file for environment variables

# Instantiate the settings object
settings = Settings()

// src/builders/NotificationBuilder.js
const EmailNotification = require("../notifications/EmailNotification");
const SMSNotification = require("../notifications/SMSNotification");
const PushNotification = require("../notifications/PushNotification");

class NotificationBuilder {
  constructor() {
    this.notification = null;
  }

  setType(type) {
    this.type = type;
    return this;
  }

  setRecipient(recipient) {
    this.recipient = recipient;
    return this;
  }

  setSubject(subject) {
    this.subject = subject;
    return this;
  }

  setMessage(message) {
    this.message = message;
    return this;
  }

  setUserId(userId) {
    this.userId = userId;
    return this;
  }

  build() {
    if (!this.type) {
      throw new Error("Notification type is required");
    }

    switch (this.type) {
      case "email":
        return new EmailNotification(this.recipient, this.subject, this.message);
      case "sms":
        return new SMSNotification(this.recipient, this.message);
      case "push":
        return new PushNotification(this.userId, this.message);
      default:
        throw new Error("Invalid notification type");
    }
  }
}

module.exports = NotificationBuilder;
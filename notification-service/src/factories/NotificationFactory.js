// src/factories/NotificationFactory.js
const EmailNotification = require("../notifications/EmailNotification");
const SMSNotification = require("../notifications/SMSNotification");
const PushNotification = require("../notifications/PushNotification");

class NotificationFactory {
  static createNotification(type, ...args) {
    switch (type) {
      case "email":
        return new EmailNotification(...args);
      case "sms":
        return new SMSNotification(...args);
      case "push":
        return new PushNotification(...args);
      default:
        throw new Error("Invalid notification type");
    }
  }
}

module.exports = NotificationFactory;
// src/services/notificationService.js
const NotificationFactory = require("../factories/NotificationFactory");
const NotificationLog = require("../models/NotificationLog");

const sendNotification = async (type, recipient, subject, message, userId) => {
  const notification = NotificationFactory.createNotification(type, recipient, subject, message, userId);
  await notification.send();

  // Log the notification in the database
  const log = new NotificationLog({
    user_id: userId,
    message_type: type,
  });
  await log.save();
};

module.exports = { sendNotification };
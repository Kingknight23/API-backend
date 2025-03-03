// src/controllers/notificationController.js
const notificationService = require("../services/notificationService");

const sendNotification = async (req, res) => {
  try {
    const { type, recipient, subject, message, userId } = req.body;
    await notificationService.sendNotification(type, recipient, subject, message, userId);
    res.status(200).json({ message: "Notification sent successfully" });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

module.exports = { sendNotification };
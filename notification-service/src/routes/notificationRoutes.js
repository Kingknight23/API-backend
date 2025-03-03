// src/routes/notificationRoutes.js
const express = require("express");
const notificationController = require("../controllers/notificationController");

const router = express.Router();

router.post("/send-notification", notificationController.sendNotification);

module.exports = router;
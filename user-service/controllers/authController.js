const User = require('../models/User');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const generateToken = (user) => {
  return jwt.sign({ id: user._id, email: user.email }, process.env.JWT_SECRET, {
    expiresIn: '7d'
  });
};

const register = async (req, res) => {
  const { name, email, password } = req.body;
  const userExists = await User.findOne({ email });
  if (userExists) return res.status(400).json({ message: 'User already exists' });

  const hashedPassword = await bcrypt.hash(password, 10);
  const user = await User.create({ name, email, password: hashedPassword });

  res.status(201).json({ token: generateToken(user), user: { name: user.name, email: user.email } });
};

const login = async (req, res) => {
  const { email, password } = req.body;
  const user = await User.findOne({ email });
  if (!user) return res.status(400).json({ message: 'Invalid credentials' });

  const match = await bcrypt.compare(password, user.password);
  if (!match) return res.status(400).json({ message: 'Invalid credentials' });

  res.json({ token: generateToken(user), user: { name: user.name, email: user.email } });
};

const getProfile = async (req, res) => {
  const user = await User.findById(req.user.id).select('-password');
  res.json(user);
};

const updateProfile = async (req, res) => {
  const user = await User.findById(req.user.id);
  if (!user) return res.status(404).json({ message: 'User not found' });

  user.name = req.body.name || user.name;
  if (req.body.password) {
    user.password = await bcrypt.hash(req.body.password, 10);
  }

  await user.save();
  res.json({ message: 'Profile updated' });
};

module.exports = {
  register,
  login,
  getProfile,
  updateProfile
};

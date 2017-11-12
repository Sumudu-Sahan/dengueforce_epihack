// server.js
// load the things we need
var express = require('express');
var app = express();

app.use(express.static('public'));

// set the view engine to ejs
app.set('view engine', 'ejs');

// use res.render to load up an ejs view file

// index page
app.get('/', function(req, res) {
  res.render('pages/index');
});

app.get('/subscriptions', function(req, res) {
  res.render('pages/subscriptions');
});

app.get('/subscription_form', function(req, res) {
  res.render('pages/subscription_form');
});

app.get('/faq', function(req, res) {
  res.render('pages/faq');
});

app.get('/emergency_numbers', function(req, res) {
  res.render('pages/emergency_numbers');
});

app.get('/video_audio', function(req, res) {
  res.render('pages/video_audio');
});

app.get('/games', function(req, res) {
  res.render('pages/games');
});

app.get('/messages', function(req, res) {
  res.render('pages/messages');
});

app.get('/about_us', function(req, res) {
  res.render('pages/about_us');
});

app.listen(8080);
console.log('App is running in 8080');

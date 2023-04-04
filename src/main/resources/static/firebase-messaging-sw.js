importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-messaging.js');

const firebaseConfig = {
    apiKey: "AIzaSyCmX-ik34fRUsDbQOOH4Oc3JD85GEBqCrg",
    authDomain: "article-notice.firebaseapp.com",
    projectId: "article-notice",
    storageBucket: "article-notice.appspot.com",
    messagingSenderId: "629960199724",
    appId: "1:629960199724:web:090903bf0ef1f4b466cb89",
    measurementId: "G-K8WSFPZ1Y7"
};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
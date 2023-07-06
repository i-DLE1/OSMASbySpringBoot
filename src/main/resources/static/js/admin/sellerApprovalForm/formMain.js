window.addEventListener('DOMContentLoaded', function() {
    var notifications = document.getElementsByClassName('notification');
    for (var i = 0; i < notifications.length; i++) {
        notifications[i].style.display = 'none'; // 알림 숨기기
    }
});

function toggleNotification(id) {
    var notification = document.getElementById(id);
    if (notification.style.display === 'none') {
        notification.style.display = 'block';
    } else {
        notification.style.display = 'none';
    }
}

function hideNotification(id) {
    var notification = document.getElementById(id);
    notification.style.display = 'none';
}
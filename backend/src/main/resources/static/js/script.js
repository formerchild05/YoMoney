function toggle() {
    const gif = document.getElementById('gif');
    if (gif.style.display === 'none') {
        gif.style.display = 'block';  // Hiện ảnh GIF
    } else {
        gif.style.display = 'none';   // Ẩn ảnh GIF
    }
}
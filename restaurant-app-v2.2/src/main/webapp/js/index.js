const modalBox = document.querySelector('.modal-box');

document.addEventListener('mouseup', (event) => {
  event.preventDefault();

  const element = event.target;

  if (element.id === 'login') {
    modalBox.classList.add('open');
  }

  if (element.classList.contains('modal-overlay')) {
    modalBox.classList.remove('open');
  }
});
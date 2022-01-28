const list = document.querySelector('.orders__list');

list.addEventListener('click', (event) => {
  event.preventDefault();

  const element = event.target;

  if (element.classList.contains('button_danger')) {
    const count = element.nextElementSibling;

    if (+count.textContent !== 0) {
      count.textContent -= 1;
    }
  }

  if (element.classList.contains('button_success')) {
    const count = element.previousElementSibling;

    count.textContent = +count.textContent + 1;
  }
});


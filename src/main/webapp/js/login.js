let noUser = document.querySelector('.noUser');

if(noUser.textContent !== 'null'){
    noUser.style.display = 'block';
    noUser.style.color = 'red';
    noUser.style.padding = '3px';
}else{
    noUser.style.display = 'none';
}
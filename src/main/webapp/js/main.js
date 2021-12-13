let user = document.querySelector('.this_user');
if(user.textContent != 'null'){
    console.log('1' +user.textContent+'1')
    document.querySelector('.login').style.display = 'none';
    document.querySelector('.registration').style.display = 'none';
    document.querySelector('.exit').style.display = 'block';
    //document.querySelector('.post').style.display = 'block';
}
else{
    document.querySelector('.login').style.display = 'block';
    document.querySelector('.registration').style.display = 'block';
    document.querySelector('.exit').style.display = 'none';
   // document.querySelector('.post').style.display = 'none';
}
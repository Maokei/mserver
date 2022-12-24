document.querySelector('#loginForm')
  .addEventListener('submit', function (event) {
  event.preventDefault();
  const theForm = event.target;
  const fields = theForm.elements;
  const usernameInput = fields.username;
  const passwordInput = fields.password;
  console.log("Attempting login: " + usernameInput.value, passwordInput.value)
    fetch ("http://localhost:8080/api/v1/login", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username: usernameInput.value,
        password: passwordInput.value
      })
    }).then((response) => {
      if(response.ok) {
        return response.json();
      } else {
        return Promise.reject(response);
      }
    }).then((data) => {
      const tknStart = "?token=";
      const { token } = data;
      console.log("Got token " + token);
      const testMedia = document.querySelector('#testMedia');
      let children = testMedia.children;
      for(let i = 0; i < children.length; i++) {
        let child = children[i];
        child.src = child.src + tknStart + token;
      }
    }).catch((err) => {
      console.warn("Login failed: ", err)
    })
});
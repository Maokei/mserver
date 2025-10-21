export const interceptor = async () => {
  const { fetch: originalFetch } = window;

  window.fetch = async (...args) => {
    let [resource, config] = args;
    const URLcutOff = 'http://localhost:8080';
    const trailing = resource.slice(URLcutOff.length);
    console.log(trailing);
    if (trailing.endsWith('/media')) {
      console.log(config);
      if (config?.headers) {
        //const h = config.headers;
        //h.authorization = 'test';
      }
      //resource = resource = '?token=';
    }
    const response = await originalFetch(resource, config);
    return response;
  };

  const orgFetch = window.fetch;

  window.fetch = function (input, init) {
    if (input) {
      console.log('Intercepting video request:', input);
      // You can modify the request headers or URL here
      // For example, you can add a custom header:
      init.headers = { ...init.headers, 'Custom-Header': 'Value' };
    }
    return orgFetch(input, init);
  };
};

import { useEffect, useState } from 'react';

const useQuery = <T>(url: string, body: object, method: string, token: string) => {
  const [data, setData] = useState<T>();
  const [isLoading, setIsLoading] = useState(false);
  const [isError, setIsError] = useState(false);

  useEffect(() => {
    getData(url, body, method, token);
  }, [url, body, method]);

  const getData = async (url: string, body: object, method: string, token: string) => {
    setIsLoading(true);
    await fetch(url, {
      method,
      headers: {
        Authorization: token ? `Bearer: ${token}` : '',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    })
      .then((res) => {
        if (res.ok) {
          return res.json();
        }
        setIsError(true);
      })
      .then((data) => {
        setData(data);
      })
      .finally(() => setIsLoading(false));
  };

  return { data, isLoading, isError };
};

export default useQuery;

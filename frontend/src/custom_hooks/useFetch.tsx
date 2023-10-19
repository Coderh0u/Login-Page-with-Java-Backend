type FetchReturn = {
  ok: boolean;
  data: any;
};

const useFetch = () => {
  const fetchData = async (
    endpoint: string,
    method: string,
    body?: object | null,
    token?: string | null,
    queryParams?: Record<string, string>
  ) => {
    const url = new URL(import.meta.env.VITE_SERVER + endpoint);
    if (queryParams) {
      url.search = new URLSearchParams(queryParams).toString();
    }

    const headers: Record<string, string> = {
      "Content-Type": "application/json",
    };
    if (token) {
      headers.Authorization = "Bearer" + token;
    }

    console.log(url.toString());

    const res = await fetch(url.toString(), {
      method,
      headers,
      body: JSON.stringify(body),
    });

    const data = await res.json();
    let returnValue: FetchReturn;

    if (res.ok) {
      if (data.status === "error") {
        returnValue = { ok: false, data: data.message };
      } else {
        returnValue = { ok: true, data };
      }
    } else {
      returnValue = { ok: false, data: data.message };
    }

    return returnValue;
  };

  return fetchData;
};

export default useFetch;

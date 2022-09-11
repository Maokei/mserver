import * as React from "react";

type MediaDataProviderProps = {
    children: React.ReactNode;
};

type MediaDataProps = {
    id: string;
    title: string;
};

type MediaDataContextProps = {
    data: MediaDataProps[];
    loading: boolean;
    error: any;
};

const MediaDataContext = React.createContext({} as MediaDataContextProps);

export function useMediaData() {
    return React.useContext(MediaDataContext);
}

export const MediaDataProvider = ({ children }: MediaDataProviderProps) => {
    const [data, setData] = React.useState([]);
    const [loading, setLoading] = React.useState(true);
    const [error, setError] = React.useState(null);

    React.useEffect(() => {
        const getData = async () => {
            try {
                const response = await fetch(
                    "http://localhost:8080/api/v1/media"
                );
                if (!response.ok) {
                    throw new Error(
                        `This is a HTTP error with a status of ${response.status}`
                    );
                }
                const data = await response.json();
                // console.log(data);
                setData(data);
                setError(null);
            } catch (error: any) {
                setError(error.message);
                setData([]);
            } finally {
                setLoading(false);
            }
        };
        getData();
    }, []);

    return (
        <MediaDataContext.Provider value={{ data, loading, error }}>
            {children}
        </MediaDataContext.Provider>
    );
};

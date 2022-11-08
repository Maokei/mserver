/**
 * Get full database URL from path
 * @param {string} path Path of the URL
 * @returns {string} Full Strapi URL
 */
export function getDatabaseURL(path = "") {
	return `${process.env.DATABASE_URL || "http://localhost:8080"}${path}`;
}

/**
 * Helper to make GET requests to Strapi API endpoints
 * */
export async function fetchLoginAPI({
	username,
	password,
}: {
	username: string;
	password: string;
}) {
	const response = await fetch("http://localhost:8080/login", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({
			username,
			password,
		}),
	});

	if (!response.ok) {
		console.error(response.statusText);
		throw new Error(`An error occured please try again`);
	}
	const data = await response.json();
	if (data.token) return data;
}

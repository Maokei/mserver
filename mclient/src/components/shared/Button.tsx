import { ReactNode } from "react";

const Button = ({
	btnClass,
	children,
	onButtonClick,
}: {
	btnClass: string;
	children: ReactNode;
	onButtonClick: React.MouseEventHandler<HTMLButtonElement>;
}) => {
	return (
		<button
			className={`button is-dark icon ${btnClass}`}
			onClick={onButtonClick}
		>
			{children}
		</button>
	);
};

export default Button;

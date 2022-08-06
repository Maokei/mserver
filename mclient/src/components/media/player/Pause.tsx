import Button from "../../shared/Button";
// import styles from "../../main/MediaButtons";

const Play = ({ handleClick }: { handleClick: Function }) => {
	return (
		<Button
			btnClass={`pause`}
			children={<i className="fas fa-pause"></i>}
			onButtonClick={() => handleClick()}
		/>
	);
};

export default Play;

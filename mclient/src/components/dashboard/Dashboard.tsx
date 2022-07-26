import { Card, CardProps } from "../shared/Card";
import { MediaPlaying } from "../main/MediaPlaying";
import styles from "./dashboard.module.scss";

type CardListProps = {
	items: CardProps[];
};

const Dashboard: React.FC<CardListProps> = ({ items }) => {
	return (
		<>
			<main className={styles.container}>
				<div className={styles.recentlyPlayed}>
					<p className="title is-4 is-size-6-mobile">
						Recently Played
					</p>

					<div className={styles.cardsGroup}>
						{[1, 2, 3, 4, 5].map((item, index) => (
							<Card key={index} />
						))}
					</div>
				</div>

				<div className={styles.trending}>
					<p className="title is-4 is-size-6-mobile">Trending</p>

					<div className={styles.cardsGroup}>
						{[1, 2, 3, 4, 5, 6].map((item, index) => (
							<Card key={index} />
						))}
					</div>
				</div>
			</main>

			<div className={styles.playingNow}>
				<MediaPlaying />
			</div>
		</>
	);
};

export default Dashboard;

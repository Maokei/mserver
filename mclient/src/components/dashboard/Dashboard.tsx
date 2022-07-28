import { Card, CardProps } from "../shared/Card";
import { MediaPlaying } from "../main/MediaPlaying";
import { MediaProgress } from "../main/MediaProgress";
import styles from "./dashboard.module.scss";

type CardListProps = {
	items: CardProps[];
};

const Dashboard: React.FC<CardListProps> = ({ items }) => {
	return (
		<>
			<main className={styles.container}>
				<aside className={styles.menu}>
					<ul className="menuTop">
						<li>
							<a href="/">Home</a>
						</li>
						<li>
							<a href="/library">Library</a>
						</li>
						<li>
							<a href="/create">Create Playlist</a>
						</li>
						<li>
							<a href="/liked">Liked</a>
						</li>
					</ul>

					<div className={styles.divider} />

					<ul className="menuBottom">
						<li>
							<a href="/playlist1">Playlist 1</a>
						</li>
						<li>
							<a href="/playlist2">Playlist 2</a>
						</li>
						<li>
							<a href="/playlist3">Playlist 3</a>
						</li>
					</ul>
				</aside>

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
				<MediaProgress />
				<MediaPlaying />
			</div>
		</>
	);
};

export default Dashboard;

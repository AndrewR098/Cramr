package com.victorlaerte.asynctask;

import javafx.application.Platform;

/**
 * This isn't our code, credit goes to the guy marked by the data below.
 * @see <a href="https://github.com/victorlaerte/javafx-asynctask">https://github.com/victorlaerte/javafx-asynctask</a>
 * @author Victor Oliveira
 */
public abstract class AsyncTask<P> {

	private boolean daemon = true;

	public abstract void onPreExecute();

	public abstract void doInBackground();

	public abstract void onPostExecute();

	public abstract void progressCallback(Object... params);

	public void publishProgress(final P... params) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				progressCallback(params);
			}
		});
	}

	private final Thread backGroundThread = new Thread(new Runnable() {

		@Override
		public void run() {

			doInBackground();

			Platform.runLater(new Runnable() {

				@Override
				public void run() {

					onPostExecute();
				}
			});
		}
	});

	public void execute() {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				onPreExecute();

				backGroundThread.setDaemon(daemon);
				backGroundThread.start();
			}
		});
	}

	public void setDaemon(boolean daemon) {

		this.daemon = daemon;
	}

	public void interrupt() {

		this.backGroundThread.interrupt();
	}
}

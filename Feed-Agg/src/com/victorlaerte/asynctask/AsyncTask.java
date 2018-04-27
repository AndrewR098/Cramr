package com.victorlaerte.asynctask;

import javafx.application.Platform;

/**
 * This isn't our code, credit goes to the guy marked by the data below.
 * @see <a href="https://github.com/victorlaerte/javafx-asynctask">https://github.com/victorlaerte/javafx-asynctask</a>
 * @author Victor Oliveira
 * @param <P> The type of progress parameter
 */
public abstract class AsyncTask<P> {

	private boolean daemon = true;

	/**
	 * Runs on main thread before background thread starts
	 */
	public abstract void onPreExecute();

	/**
	 * Runs on background thread. This executes after
	 * {@linkplain #onPreExecute()} and before
	 * {@linkplain #onPostExecute()}.
	 */
	public abstract void doInBackground();

	/**
	 * Runs after background thread ends on the 
	 * main thread
	 */
	public abstract void onPostExecute();

	/**
	 * Use this to update anything on the main thread as
	 * the background thread runs (this is a callback, DO NOT
	 * CALL THIS DIRECTLY)
	 * @param params parameters to pass, can be anything you want.
	 */
	public abstract void progressCallback(P... params);

	/**
	 * Call this to publish progress to the main thread
	 * @param params parameters to pass, can be anything you want
	 */
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

	/**
	 * Start the Async Task
	 */
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

	/**
	 * Set if this should be a daemon thread or not
	 * @param daemon is daemon?
	 * @see {@linkplain Thread#setDaemon(boolean)}
	 */
	public void setDaemon(boolean daemon) {

		this.daemon = daemon;
	}

	/**
	 * Interrupt the thread.
	 * @see {@linkplain Thread#interrupt()}
	 */
	public void interrupt() {

		this.backGroundThread.interrupt();
	}
}

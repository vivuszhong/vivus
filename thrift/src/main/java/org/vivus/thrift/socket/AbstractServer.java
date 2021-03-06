package org.vivus.thrift.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractServer implements Server {
	static Logger logger = LoggerFactory.getLogger(AbstractServer.class);

	protected int port;
	private volatile boolean stoped = true;

	public AbstractServer(int port) {
		this.port = port;
	}

	@Override
	public void start() {
		stoped = false;
		logger.info("======== starting server ========");
		start_();
	}

	protected abstract void start_();

	protected void stop_() {
	}

	@Override
	public void stop() {
		logger.info("======== stoping server ========");
		stoped = true;
		stop_();
		logger.info("======== server stop ========");
	}

	public boolean isStop() {
		return stoped;
	}
}

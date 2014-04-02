package poolingpeople.webapplication.business.boundary;

import poolingpeople.commons.entities.Task;

public class UpdateTask {

	private Task updatedTask;
	private Task oldTask;

	public UpdateTask(Task oldTask, Task updatedTask) {
		this.oldTask = oldTask;
		this.updatedTask = updatedTask;
	}

	public Task getUpdatedTask() {
		return updatedTask;
	}

	public Task getOldTask() {
		return oldTask;
	}

}

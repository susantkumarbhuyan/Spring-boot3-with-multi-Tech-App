package com.hsignz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;


@Component
public class CamundaUtils {
	@Autowired
	private ZeebeClient client;

	public String startBPMNProcess(Object variables, String bpmnProcessId) {
		// to await the completion of process execution and return result
		final ProcessInstanceResult processInstanceResult = client.newCreateInstanceCommand()
				.bpmnProcessId(bpmnProcessId).latestVersion().variables(variables).withResult().send().join();
		System.out.println("Process instance created with key: " + processInstanceResult.getProcessInstanceKey()
				+ " and completed with results: PocDetails ??>>>>>>> " + processInstanceResult.getVariables());
		return processInstanceResult.getVariables();
	}
}

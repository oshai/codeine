package codeine.servlets.api_servlets.angular;

import java.util.List;

import codeine.api.NodeWithMonitorsInfo;
import codeine.jsons.collectors.CollectorExecutionInfo;
import codeine.jsons.collectors.CollectorInfo.CollectorType;
import codeine.model.Constants;
import codeine.utils.StringUtils;

import com.google.common.collect.Lists;

@SuppressWarnings("unused")
public class NodeWithMonitorsInfoApi extends NodeWithMonitorsInfo {

	private List<String> failed_collectors = Lists.newArrayList();
	private List<CollectorInfoForUI> collectors_info = Lists.newArrayList();
	private boolean user_can_command;
	
	public NodeWithMonitorsInfoApi(NodeWithMonitorsInfo info, boolean user_can_command) 
	{
		super(info);
		this.user_can_command = user_can_command;
		for (CollectorExecutionInfo collectorInfo : info.collectors().values()) {
			if (shouldShowInStatusPage(collectorInfo)) {
				collectors_info.add(new CollectorInfoForUI(collectorInfo.name(), collectorInfo.value(), collectorInfo.exit_status()));
			}
			if (!collectorInfo.isSuccess()) {
				failed_collectors.add(collectorInfo.name());
			}
		}
	}

	private boolean shouldShowInStatusPage(CollectorExecutionInfo collectorInfo) {
		if (collectorInfo.type()== CollectorType.Monitor){
			return !collectorInfo.isSuccess();
		}
		if (!collectorInfo.isSuccess() && shouldDisplayByName(collectorInfo.name())){
			return true;
		}
		return !StringUtils.isEmpty(collectorInfo.value()) && shouldDisplayByName(collectorInfo.name());
	}
	
	private boolean shouldDisplayByName(String name) {
		if (name.endsWith(Constants.VERSION_COLLECTOR_NAME) || name.endsWith(Constants.TAGS_COLLECTOR_NAME)) {
			return false;
		}
		return true;
	}

	private static class CollectorInfoForUI {
		private String name;
		private String value;
		private int exit_status;
		public CollectorInfoForUI(String name, String value, int exit_status) {
			this.name = name;
			this.value = value;
			this.exit_status = exit_status;
		}
	}
}
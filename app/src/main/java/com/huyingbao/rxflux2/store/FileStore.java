package com.huyingbao.rxflux2.store;

import com.huyingbao.rxflux2.action.RxAction;
import com.huyingbao.rxflux2.constant.Actions;
import com.huyingbao.rxflux2.constant.ActionsKeys;
import com.huyingbao.rxflux2.dispatcher.Dispatcher;

import java.util.List;

/**
 * Created by liujunfeng on 2017/1/1.
 */
public class FileStore extends RxStore {
    private String upToken;
    private String fileKey;
    private List<String> fileKeyList;

    public FileStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    /**
     * This callback will get all the rxActions, each store must react on the types he want and do
     * some logic with the model, for example add it to the list to cache it, modify
     * fields etc.. all the logic for the models should go here and then call postChange so the
     * view request the new data
     * 这个回调接收所有的actions(RxAction),每个store都必须根据action的type做出反应,,例如将其添加到列表缓存,修改字段等。
     * 所有的逻辑模型应该在这里,然后调用postChange请求新数据视图
     */
    @Override
    public void onRxAction(RxAction rxAction) {
        switch (rxAction.getType()) {
            case Actions.GET_UP_TOKEN:
                upToken = rxAction.get(ActionsKeys.UP_TOKEN);
                break;
//            case Actions.UPLOAD_ONE_FILE:
//                fileKey = rxAction.get(ActionsKeys.FILE_KEY);
//                break;
//            case Actions.UPLOAD_All_FILE:
//                fileKeyList = rxAction.get(ActionsKeys.FILE_KEY_LIST);
            default:
                return;
        }
        postChange(new RxStoreChange(getClass().getSimpleName(), rxAction));
    }

    public String getUpToken() {
        return upToken;
    }

    public String getFileKey() {
        return fileKey;
    }

    public List<String> getFileKeyList() {
        return fileKeyList;
    }
}

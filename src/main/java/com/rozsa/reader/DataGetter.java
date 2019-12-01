package com.rozsa.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rozsa.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class DataGetter
{
    public static List<InteractionData> getInteractionsData(HttpServletRequest req, PassiveNpcData npc) {
        List<Integer> interactionsId = getParametersValueAsInteger(req,"interactions_interaction_id");

        List<InteractionData> interactions = new ArrayList<>();
        for (Integer id : interactionsId) {
            InteractionData oldData = npc.getInteractionDataByid(id);
            InteractionData data = getInteractionData(req, id, oldData.getMessagesIds());
            interactions.add(data);
        }

        return interactions;
    }

    public static  InteractionData getInteractionData(HttpServletRequest req, int id, List<Integer> messagesIds) {
        InteractionData data = new InteractionData();
        data.setId(id);
        data.setType(InteractionType.valueOf(getString(req, "interactions_interactiontype" + id)));
        data.setPriceMultiplier(getFloat(req, "interactions_interaction_pricemultiplier" + id));
        data.setRequireEventsState(getEventState(req, "interactions_interaction_requiredeventstate" + id));
        data.setUpdateEventsState(getEventState(req, "interactions_interaction_updateeventstate" + id));
        data.setItems(getStringList(req, "interactions_interaction_items" + id));

        data.setTargetId(getInteger(req, "interactions_interaction_teleportTargetId" + id));
        Integer posX = getInteger(req, "interactions_interaction_teleportPosX" + id);
        Integer posY = getInteger(req, "interactions_interaction_teleportPosY" + id);
        Offset targetPos = new Offset(posX, posY);
        data.setToPos(targetPos);

        List<DialogFeedbackData> messages = getMessages(req, messagesIds);
        data.setMessages(messages);

        return data;
    }

    public static  List<DialogFeedbackData> getMessages(HttpServletRequest req, List<Integer> messagesIds) {
        List<DialogFeedbackData> messages = new ArrayList<>();

        for(Integer id : messagesIds) {
            DialogFeedbackData message = getMessage(req, id);
            messages.add(message);
        }

        return messages;
    }

    public static  DialogFeedbackData getMessage(HttpServletRequest req, Integer id) {
        DialogFeedbackData message = new DialogFeedbackData();
        message.setId(id);
        message.setTitle(getLabelData(req, "interactions_messages_title"+id));
        message.setText(getLabelData(req, "interactions_messages_text"+id));

        return message;
    }

    public static  LabelData getLabelData(HttpServletRequest req, String key) {
        LabelData label = new LabelData();
        label.setText(getString(req, key + "_text"));
        label.setFont(getString(req, key + "_fonttype"));
        label.setStyle(getInteger(req, key + "_fontstyle"));
        label.setSize(getInteger(req, key + "_size"));
        label.setColor(getColor(req, key + "_color"));
        label.setVerticalAlignment(getInteger(req, key + "_verticalAlignment"));
        label.setHorizontalAlignment(getInteger(req, key + "_horizontalAlignment"));
        label.setRect(getRect(req, key + "_rect"));

        return label;
    }

    public static  ColorData getColor(HttpServletRequest req, String key) {
        String colorHex = req.getParameter(key);
        return new ColorData(
                Integer.valueOf( colorHex.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorHex.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorHex.substring( 5, 7 ), 16 ) );
    }

    public static  Rect getRect(HttpServletRequest req, String key) {
        return new Rect(
                getInteger(req, key+"_X"),
                getInteger(req, key+"_Y"),
                getInteger(req, key+"_W"),
                getInteger(req, key+"_H")
        );
    }

    public static  List<EventData> getEventState(HttpServletRequest req, String key) {
        String rawRequiredEventState = req.getParameter(key);
        if (rawRequiredEventState == null || rawRequiredEventState.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder sb = new StringBuilder("[");
        sb.append(rawRequiredEventState);
        sb.append("]");
        String json = sb.toString();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            EventData[] data = objectMapper.readValue(json, EventData[].class);
            return Arrays.asList(data);
        } catch (Exception ex) {
            System.out.println("Failed to parse event data from json!");
        }

        return new ArrayList<>();
    }
    public static  Float getFloat(HttpServletRequest req, String key) {
        return Float.parseFloat(req.getParameter(key));
    }

    public static  Integer getInteger(HttpServletRequest req, String key) {
        return Integer.parseInt(req.getParameter(key));
    }

    public static  String getString(HttpServletRequest req, String key) {
        return req.getParameter(key);
    }

    public static  Boolean getBoolean(HttpServletRequest req, String key) {
        String enabledText = req.getParameter(key);
        return enabledText != null && enabledText.equals("on");
    }

    public static  List<Integer> getIntegerList(HttpServletRequest req, String key) {
        String rawData = req.getParameter(key);
        if (rawData == null || rawData.isEmpty()) {
            return new ArrayList<>();
        }

        String[] tokens = rawData.split(",");

        return convertStringToInteger(tokens);
    }

    public static  List<String> getStringList(HttpServletRequest req, String key) {
        String rawData = req.getParameter(key);
        if (rawData == null || rawData.isEmpty()) {
            return new ArrayList<>();
        }

        String[] tokens = rawData.split(",");

        List<String> tokensAsList = Arrays.asList(tokens);
        tokensAsList = tokensAsList
                            .stream()
                            .map(t -> t.trim())
                            .collect(Collectors.toList());
        return tokensAsList;
    }

    public static  List<Integer> getParametersValueAsInteger(HttpServletRequest req, String key) {
        List<Integer> targetList = new ArrayList<>();

        String[] tokens = req.getParameterValues(key);
        if (tokens == null || tokens.length == 0) {
            return targetList;
        }

        return convertStringToInteger(tokens);
    }

    public static  List<Integer> convertStringToInteger(String[] values) {
        List<Integer> targetList = new ArrayList<>();
        for(String value : values) {
            targetList.add(Integer.parseInt(value.trim()));
        }
        return targetList;
    }
}

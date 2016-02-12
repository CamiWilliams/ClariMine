package com.cami.ClariMine.client;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.cami.ClariMine.client.capture.task.RenderTickTask;
import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class ScreenshotHandler {
    
    private static final Minecraft MC = Minecraft.getMinecraft();
    private static final String KEY_CATEGORY = "key.categories.clarimine";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    
    private final KeyBinding keyCapture = new KeyBinding("key.clarimine.capture", Keyboard.KEY_C, KEY_CATEGORY);    
    private RenderTickTask task;

    public ScreenshotHandler() {
        ClientRegistry.registerKeyBinding(keyCapture);
    }
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (task != null) {
            return;
        }
        
        String curr = DATE_FORMAT.format(new Date()) + ".png";
        System.out.println(curr + " " + Keyboard.isKeyDown(Keyboard.KEY_F2));
        
        if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
        	ClarifaiClient clarifai = new ClarifaiClient("VUInMKUpiPOs003rTVyJ1Ha0f1gSgA906uZJdPe0", "V0fYG3F-WgNMyeWiV1nznqJCBYrWBu-IxxeDFP1P");
        	List<RecognitionResult> results = clarifai.recognize(new RecognitionRequest(new File(MC.mcDataDir, "screenshots/"+  curr)));
        	String clarifaiMessage = "";
        	for (int i = 0; i < 8; i++) {
        		Tag tag = results.get(0).getTags().get(i);
        		clarifaiMessage += tag.getName() + ", ";
            }
        	clarifaiMessage += "via Clarifai";
        	System.out.println(clarifaiMessage);
        	Minecraft.getMinecraft().thePlayer.sendChatMessage(clarifaiMessage);
        }
    }
}

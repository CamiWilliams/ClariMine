package com.cami.ClariMine.client.capture.task;

import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public interface RenderTickTask {
    
    /**
     * Called on every frame to update the task.
     * 
     * @param evt current render tick event
     * @return true if the task is done and can be disposed or false if it should
     *         continue to be updated.
     * @throws Exception 
     */
    boolean onRenderTick(RenderTickEvent evt) throws Exception;
}

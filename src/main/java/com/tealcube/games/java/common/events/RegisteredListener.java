/*
 * This file is part of TealCube-Commons, licensed under the ISC License.
 *
 * Copyright (c) 2015 Teal Cube Games <tealcubegames@gmail.com>
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */
package com.tealcube.games.java.common.events;

public class RegisteredListener {

    private final Listener listener;
    private final EventPriority priority;
    private final EventExecutor executor;
    private final boolean ignoreCancelled;

    public RegisteredListener(Listener listener, EventPriority priority, EventExecutor executor,
                              boolean ignoreCancelled) {
        this.listener = listener;
        this.priority = priority;
        this.executor = executor;
        this.ignoreCancelled = ignoreCancelled;
    }

    public void callEvent(Event event) throws EventException {
        if (event instanceof Cancellable) {
            if (((Cancellable) event).isCancelled() && isIgnoreCancelled()) {
                return;
            }
        }
        executor.execute(listener, event);
    }

    public Listener getListener() {
        return listener;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public EventExecutor getExecutor() {
        return executor;
    }

    public boolean isIgnoreCancelled() {
        return ignoreCancelled;
    }
}

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package images.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

/** ScrollablePicture.java is used by ScrollDemo.java. */
public class ScrollablePicture extends JLabel implements Scrollable {

  /** Generated version id. */
  private static final long serialVersionUID = 1155266866575390163L;

  private int maxUnitIncrement = 1;
  private boolean missingPicture = false;

  /**
   * Creates a scrollable picture with an image icon.
   *
   * @param i the image icon to use
   * @param m the increment unit
   */
  public ScrollablePicture(ImageIcon i, int m) {
    super(i);
    if (i == null) {
      missingPicture = true;
      setText("No picture found.");
      setHorizontalAlignment(CENTER);
      setOpaque(true);
      setBackground(Color.white);
    }
    maxUnitIncrement = m;

    // Let the user scroll by dragging to outside the window.
    setAutoscrolls(true); // enable synthetic drag events
  }

  /**
   * Sets the maximum unit increment.
   *
   * @param pixels the new value
   */
  public void setMaxUnitIncrement(int pixels) {
    maxUnitIncrement = pixels;
  }

  @Override
  public Dimension getPreferredSize() {
    if (missingPicture) {
      return new Dimension(220, 380);
    } else {
      return super.getPreferredSize();
    }
  }

  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return getPreferredSize();
  }

  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    // Get the current position.
    int currentPosition = 0;
    if (orientation == SwingConstants.HORIZONTAL) {
      currentPosition = visibleRect.x;
    } else {
      currentPosition = visibleRect.y;
    }

    // Return the number of pixels between currentPosition
    // and the nearest tick mark in the indicated direction.
    if (direction < 0) {
      int newPosition = currentPosition - (currentPosition / maxUnitIncrement) * maxUnitIncrement;
      return (newPosition == 0) ? maxUnitIncrement : newPosition;
    } else {
      return ((currentPosition / maxUnitIncrement) + 1) * maxUnitIncrement - currentPosition;
    }
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    if (orientation == SwingConstants.HORIZONTAL) {
      return visibleRect.width - maxUnitIncrement;
    } else {
      return visibleRect.height - maxUnitIncrement;
    }
  }

  @Override
  public boolean getScrollableTracksViewportWidth() {
    return false;
  }

  @Override
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }
}

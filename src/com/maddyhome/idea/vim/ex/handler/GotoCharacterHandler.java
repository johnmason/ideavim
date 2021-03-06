package com.maddyhome.idea.vim.ex.handler;

/*
 * IdeaVim - A Vim emulator plugin for IntelliJ Idea
 * Copyright (C) 2003-2005 Rick Maddy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.ex.CommandHandler;
import com.maddyhome.idea.vim.ex.ExCommand;
import com.maddyhome.idea.vim.group.CommandGroups;
import com.maddyhome.idea.vim.group.MotionGroup;

/**
 *
 */
public class GotoCharacterHandler extends CommandHandler {
  public GotoCharacterHandler() {
    super("go", "to", RANGE_OPTIONAL | ARGUMENT_OPTIONAL | RANGE_IS_COUNT, Command.FLAG_MOT_EXCLUSIVE);
  }

  public boolean execute(Editor editor, DataContext context, ExCommand cmd) {
    int count = cmd.getCount(editor, context, 1, true);

    if (count > 0) {
      int res = CommandGroups.getInstance().getMotion().moveCaretToNthCharacter(editor, count - 1);
      if (res != -1) {
        MotionGroup.moveCaret(editor, context, res);

        return true;
      }
    }

    return false;
  }
}

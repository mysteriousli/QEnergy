//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package main.java.net.friend.service;

import main.java.net.friend.dao.FabulousDao;
import main.java.net.friend.dao.FabulousDaoImpl;
import main.java.net.friend.entity.Fabulous;

public class LikeService {
    public LikeService() {
    }

    public boolean likePost(int userId, int postId) {
        FabulousDao fabulousDao = new FabulousDaoImpl();
        if (fabulousDao.isLike(userId, postId)) {
            fabulousDao.delete(userId, postId);
            return true;
        }
        Fabulous fabulous = new Fabulous();
        fabulous.setFabPostId(postId);
        fabulous.setFabUserId(userId);
        fabulousDao.insert(fabulous);
        return false;
    }
}

(ns codenames-cljs.subs
  (:require [com.rpl.specter :as S]
            [re-frame.core :as re-frame]))

(defn cell-filterer
  [[x y :as target] {:keys [position]}]
  (= target position))

(defn get-cell
  [db x y]
  (S/select-any [:game S/ATOM :words (S/filterer #(cell-filterer [x y] %)) S/ALL] db))

(defn get-current-team
  [db]
  (S/select-any [:game S/ATOM :current-team] db))

(defn get-revealed-status
  [db]
  (S/select-any [:game S/ATOM :revealed?] db))

(defn get-view
  [db]
  (S/select-any [:game S/ATOM :view] db))

(defn get-winner
  [db]
  (S/select-any [:game S/ATOM :winning-team] db))

(re-frame/reg-sub
 :cell
 (fn [db [_ x y]]
   (get-cell db x y)))

(re-frame/reg-sub
 :game
 :game)

(re-frame/reg-sub
 :revealed
 (fn [db _]
   (get-revealed-status db)) )

(re-frame/reg-sub
 :turn
 (fn [db _]
   (get-current-team db)))

(re-frame/reg-sub
 :view
 (fn [db _]
   (get-view db)))

(re-frame/reg-sub
 :winner
 (fn [db _]
   (get-winner db)))

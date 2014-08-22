(ns bug.core
  (:require
   [om.core :as om :include-macros true]
   [om.dom :as dom :include-macros true]))

(enable-console-print!)
(def el (js/document.getElementById "main"))

(def app-state (atom {:something "foo"}))

(declare login-component)
(declare main-component)

(defn show-login []
  (om/root login-component
           app-state
           {:target el}))

(defn show-main []
  (om/root main-component
           app-state
           {:target el}))

(defn login-component [app-state owner]
  (reify
    om/IWillUnmount (will-unmount [_] (print "login will unmount"))
    om/IDidMount (did-mount [_] (print "login did mount"))
    om/IRender
    (render
     [_]
     (letfn [(transition
              []
              (om/update! app-state :something "bar")
              (show-main))
             (transition-workaround
              []
              (show-main)
              (om/update! app-state :something "bar")
              )]

       (dom/div nil
                "Try one of these transitions... "
                (dom/button #js {:onClick transition} "update, then change root")
                (dom/button #js {:onClick transition-workaround} "change root, then update"))
       ))))

(defn main-component [app-state owner]
  (reify
    om/IWillUnmount (will-unmount [_] (print "main will unmount"))
    om/IDidMount (did-mount [_] (print "main did mount"))
    om/IRender
    (render
     [_]
     (dom/div nil "alright"))))

(show-login)

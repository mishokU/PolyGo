package com.mishok.feature_stream_impl

import android.content.Context
import android.graphics.Canvas
import android.view.ViewGroup
import com.mishok.feature_stream_impl.org.appspot.apprtc.AppRTCClient
import com.mishok.feature_stream_impl.org.appspot.apprtc.PeerConnectionClient
import org.webrtc.IceCandidate
import org.webrtc.SessionDescription
import org.webrtc.StatsReport


class ARStreamView(context: Context?) : ViewGroup(context), AppRTCClient.SignalingEvents,
    PeerConnectionClient.PeerConnectionEvents {


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    override fun onConnectedToRoom(params: AppRTCClient.SignalingParameters?) {

    }

    override fun onRemoteDescription(sdp: SessionDescription?) {
        TODO("Not yet implemented")
    }

    override fun onRemoteIceCandidate(candidate: IceCandidate?) {

    }

    override fun onChannelClose() {

    }

    override fun onChannelError(description: String?) {

    }

    override fun onRemoteIceCandidatesRemoved(candidates: Array<out IceCandidate>?) {

    }

    override fun onLocalDescription(sdp: SessionDescription?) {

    }

    override fun onIceCandidate(candidate: IceCandidate?) {

    }

    override fun onIceCandidatesRemoved(candidates: Array<out IceCandidate>?) {

    }

    override fun onIceConnected() {

    }

    override fun onIceDisconnected() {

    }

    override fun onPeerConnectionClosed() {

    }

    override fun onPeerConnectionStatsReady(reports: Array<out StatsReport>?) {

    }

    override fun onPeerConnectionError(description: String?) {

    }
}
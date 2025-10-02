import './SButton.css';


export default function SButton() {
    return (
        <label className="premium-toggle">
            <input type="checkbox" />
            <div className="switch">
                <div className="track">
                    <div className="magnetic-field"></div>
                    <div className="track-shadow"></div>
                </div>
                <div className="handle-container">
                    <div className="handle">
                        <div className="handle-inner">
                            <div className="handle-ripple"></div>
                            <div className="handle-shine"></div>
                            <div className="handle-texture"></div>
                            <div className="sun-moon">
                                <div className="ray ray1"></div>
                                <div className="ray ray2"></div>
                                <div className="ray ray3"></div>
                                <div className="ray ray4"></div>
                                <div className="crater crater1"></div>
                                <div className="crater crater2"></div>
                                <div className="crater crater3"></div>
                            </div>
                        </div>
                        <div className="wave-group">
                            <div className="wave wave1"></div>
                            <div className="wave wave2"></div>
                            <div className="wave wave3"></div>
                        </div>
                    </div>
                </div>
            </div>
        </label>

    );
}
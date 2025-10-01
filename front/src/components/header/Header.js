import "./styles.css";

export default function Header({user}) {
    return (
        <div className="header">
            <img className="profile-picture" src="/image.png" alt={user?.username || "User"} />
            <h1 style={{margin: 0, fontSize: 26}}>Expense Tracker</h1>
            <p className="Welcome" style={{margin: 0}}>
                Welcome, {user?.fullName || user?.username || "Guest"}!
            </p>
        </div>
    );
}

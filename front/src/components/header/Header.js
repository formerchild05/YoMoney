import "./styles.css";


export default function Header({user}) {
    return (
        <div className="header">
            <img className="profile-picture" src={user.profilePicture} alt={user.name} />
            <h1 style={{margin: 0, fontSize: 26}}>Expense Tracker</h1>
            <p className="Welcome" style={{margin: 0}}>Welcome, {user.name}!</p>
        </div>
    );
}

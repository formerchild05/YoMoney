import Header from "./header/Header";
import Revenue from "./body/revenue";
export default function MainPage({user}) {
    return (
        <div>
            <Header user={user} />
            <Revenue />
        </div>
    );
}